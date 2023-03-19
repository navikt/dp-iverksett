# language: no
# encoding: UTF-8

Egenskap: 2 perioder med endring på en av periodene

  Scenariomal: Har 2 perioder og får en endring på andre perioden

    Gitt følgende tilkjente ytelser for Dagpenger
      | BehandlingId | Fra dato   | Til dato   | Beløp |
      | 1            | 01.02.2021 | 30.04.2021 | 700   |
      | 1            | 01.05.2021 | 31.07.2021 | 900   |
      | 2            | 01.02.2021 | 30.04.2021 | 700   |
      | 2            | <Fra dato> | 30.06.2021 | 900   |

    Når lagTilkjentYtelseMedUtbetalingsoppdrag kjøres

    Så forvent følgende utbetalingsoppdrag
      | BehandlingId | Fra dato   | Til dato   | Opphørsdato | Beløp | Kode endring | Er endring | Periode id | Forrige periode id |
      | 1            | 01.02.2021 | 30.04.2021 |             | 700   | NY           | Nei        | 1          |                    |
      | 1            | 01.05.2021 | 31.07.2021 |             | 900   | NY           | Nei        | 2          | 1                  |
      | 2            | 01.05.2021 | 31.07.2021 |01.05.2021    | 900   | ENDR         | Ja         | 2          | 1                  |
      | 2            | <Fra dato> | 30.06.2021 |             | 900   | ENDR         | Nei        | 3          | 2                  |

    Så forvent følgende tilkjente ytelser for behandling 1 med startdato 01.02.2021
      | Fra dato   | Til dato   | Beløp | Periode id | Forrige periode id | Kilde behandling id |
      | 01.02.2021 | 30.04.2021 | 700   | 1          |                    | 1                   |
      | 01.05.2021 | 31.07.2021 | 900   | 2          | 1                  | 1                   |

    Så forvent følgende tilkjente ytelser for behandling 2 med startdato 01.02.2021
      | Fra dato   | Til dato   | Beløp | Periode id | Forrige periode id | Kilde behandling id |
      | 01.02.2021 | 30.04.2021 | 700   | 1          |                    | 1                   |
      | <Fra dato> | 30.06.2021 | 900   | 3          | 2                  | 2                   |

    Eksempler:
      | Fra dato   |
      | 01.05.2021 |
      | 01.06.2021 |