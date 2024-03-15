package no.nav.dagpenger.iverksett.utbetaling.tilstand

import no.nav.dagpenger.iverksett.utbetaling.domene.Iverksetting
import no.nav.dagpenger.iverksett.utbetaling.domene.IverksettingEntitet
import no.nav.dagpenger.iverksett.utbetaling.domene.sakId
import no.nav.dagpenger.kontrakter.felles.objectMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.sql.ResultSet

@Repository
class IverksettingRepository(private val jdbcTemplate: JdbcTemplate) {
    fun insert(iverksetting: IverksettingEntitet): IverksettingEntitet {
        val sql = "insert into iverksetting (behandling_id, data, mottatt_tidspunkt) values (?,to_json(?::json), ?)"
        jdbcTemplate.update(
            sql,
            iverksetting.behandlingId,
            objectMapper.writeValueAsString(iverksetting.data),
            iverksetting.mottattTidspunkt,
        )
        return iverksetting
    }

    fun findByFagsakId(fagsakId: String): List<IverksettingEntitet> {
        val sql =
            "select behandling_id, data, mottatt_tidspunkt from iverksetting where data -> 'fagsak' ->> 'fagsakId' = ?"
        return jdbcTemplate.query(sql, IverksettingRowMapper(), fagsakId)
    }

    fun findByFagsakAndBehandlingAndIverksetting(
        fagsakId: String,
        behandlingId: String,
        iverksettingId: String?,
    ): List<IverksettingEntitet> {
        return if (iverksettingId != null) {
            findByFagsakIdAndBehandlingIdAndIverksettingId(fagsakId, behandlingId, iverksettingId)
        } else {
            findByFagsakIdAndBehandlingId(fagsakId, behandlingId)
        }
    }

    private fun findByFagsakIdAndBehandlingIdAndIverksettingId(
        fagsakId: String,
        behandlingId: String,
        iverksettingId: String,
    ): List<IverksettingEntitet> {
        val sql =
            "select behandling_id, data, mottatt_tidspunkt from iverksetting where behandling_id = ? " +
                "and data -> 'fagsak' ->> 'fagsakId' = ? and data -> 'behandling' ->> 'iverksettingId' = ?"
        return jdbcTemplate.query(sql, IverksettingRowMapper(), behandlingId, fagsakId, iverksettingId)
    }

    private fun findByFagsakIdAndBehandlingId(
        fagsakId: String,
        behandlingId: String,
    ): List<IverksettingEntitet> {
        val sql =
            "select behandling_id, data, mottatt_tidspunkt from iverksetting where behandling_id = ? " +
                "and data -> 'fagsak' ->> 'fagsakId' = ? and data -> 'behandling' ->> 'iverksettingId' is null"
        return jdbcTemplate.query(sql, IverksettingRowMapper(), behandlingId, fagsakId)
    }

    fun findByEmptyMottattTidspunkt(): List<IverksettingEntitet> {
        val sql = "select behandling_id, data, mottatt_tidspunkt from iverksetting where mottatt_tidspunkt is null"
        return jdbcTemplate.query(sql, IverksettingRowMapper())
    }

    fun settMottattTidspunktForIverksetting(iverksetting: IverksettingEntitet) {
        if (iverksetting.data.behandling.iverksettingId == null) {
            val sql =
                "update iverksetting set mottatt_tidspunkt = ? where behandling_id = ? " +
                    "and data -> 'fagsak' -> 'fagsakId' ->> 'id' = ? and data -> 'behandling' ->> 'iverksettingId' is null"
            jdbcTemplate.update(
                sql,
                iverksetting.mottattTidspunkt,
                iverksetting.behandlingId,
                iverksetting.data.sakId,
            )
        } else {
            val sql =
                "update iverksetting set mottatt_tidspunkt = ? where behandling_id = ? " +
                    "and data -> 'fagsak' -> 'fagsakId' ->> 'id' = ? and data -> 'behandling' ->> 'iverksettingId' = ?"
            jdbcTemplate.update(
                sql,
                iverksetting.mottattTidspunkt,
                iverksetting.behandlingId,
                iverksetting.data.sakId,
                iverksetting.data.behandling.iverksettingId,
            )
        }
    }
}

internal class IverksettingRowMapper : RowMapper<IverksettingEntitet> {
    override fun mapRow(
        resultSet: ResultSet,
        rowNum: Int,
    ) = IverksettingEntitet(
        behandlingId = resultSet.getString("behandling_id"),
        data = objectMapper.readValue(resultSet.getString("data"), Iverksetting::class.java),
    mottattTidspunkt = resultSet.getTimestamp("mottatt_tidspunkt")?.toLocalDateTime(),)
}
