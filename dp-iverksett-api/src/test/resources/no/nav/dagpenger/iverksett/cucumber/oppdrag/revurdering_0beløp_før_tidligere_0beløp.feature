# language: no
# encoding: UTF-8

Egenskap: Revurderer med 0 beløp etter en revurdering med 0 beløp skal sette startdato på riktig plass

  Scenario: Revurderer med 0 beløp etter en revurdering med 0 beløp skal sette startdato på riktig plass

    Gitt følgende tilkjente ytelser for Dagpenger
      | BehandlingId | Fra dato | Til dato | Beløp |
      | 1            |01.03.2021 |01.03.2021 | 700   |
      | 2            |01.02.2021 |01.04.2021 | 0     |
      | 3            |01.01.2021 |01.01.2021 | 0     |
      | 3            |01.01.2021 |01.04.2021 | 0     |

    Når lagTilkjentYtelseMedUtbetalingsoppdrag kjøres

    Så forvent følgende utbetalingsoppdrag
      | BehandlingId | Fra dato | Til dato | Opphørsdato | Beløp | Kode endring | Er endring | Periode id | Forrige periode id |
      | 1            |01.03.2021 |01.03.2021 |             | 700   | NY           | Nei        | 1          |                    |
      | 2            |01.03.2021 |01.03.2021 |01.02.2021    | 700   | ENDR         | Ja         | 1          |                    |
      | 3            |01.03.2021 |01.03.2021 |01.01.2021    | 700   | ENDR         | Ja         | 1          |                    |


    Og forvent følgende tilkjente ytelser for behandling 1 med startdato 01.03.2021
      | Fra dato | Til dato | Beløp | Periode id | Forrige periode id | Kilde behandling id |
      |01.03.2021 |01.03.2021 | 700   | 1          |                    | 1                   |

    Og forvent følgende tilkjente ytelser for behandling 2 med startdato 01.02.2021
      | Fra dato | Til dato | Beløp | Periode id | Forrige periode id | Kilde behandling id |
      |          |          | 0     | 1          |                    | 2                   |

    Og forvent følgende tilkjente ytelser for behandling 3 med startdato 01.01.2021
      | Fra dato | Til dato | Beløp | Periode id | Forrige periode id | Kilde behandling id |
      |          |          | 0     | 1          |                    | 3                   |
