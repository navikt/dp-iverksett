# language: no
# encoding: UTF-8

Egenskap: Førstegangsbehandling - kan sende startdato i en førstegangsbehandling

  Scenario: Førstegangsbehandling - kan sende startdato i en førstegangsbehandling

    Gitt følgende tilkjente ytelser for Dagpenger
      | BehandlingId | Fra dato | Til dato | Beløp |
      | 1            |01.03.2021 |01.03.2021 | 0     |
      | 1            |01.03.2021 |01.03.2021 | 700   |

    Når lagTilkjentYtelseMedUtbetalingsoppdrag kjøres

    Så forvent følgende utbetalingsoppdrag
      | BehandlingId | Fra dato | Til dato | Opphørsdato | Beløp | Kode endring | Er endring | Periode id | Forrige periode id |
      | 1            |01.03.2021 |01.03.2021 |             | 700   | NY           | Nei        | 1          |                    |

    Så forvent følgende tilkjente ytelser for behandling 1 med startdato 01.03.2021
      | Fra dato | Til dato | Beløp | Periode id | Forrige periode id | Kilde behandling id |
      |01.03.2021 |01.03.2021 | 700   | 1          |                    | 1                   |


