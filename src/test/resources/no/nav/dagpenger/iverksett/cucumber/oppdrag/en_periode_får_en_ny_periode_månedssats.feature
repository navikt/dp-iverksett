# language: no
# encoding: UTF-8

Egenskap: Månedssats, enkelt scenario

  Scenario: Har en periode, legger til en ny

    Gitt følgende tilkjente ytelser
      | BehandlingId | Fra dato   | Til dato   | Beløp | Satstype |
      | 1            | 01.02.2021 | 31.03.2021 | 700   | MÅNEDLIG |
      | 2            | 01.02.2021 | 31.03.2021 | 700   | MÅNEDLIG |
      | 2            | 01.04.2021 | 31.05.2021 | 900   | MÅNEDLIG |

    Når beregner utbetalingsoppdrag

    Så forvent følgende utbetalingsoppdrag
      | BehandlingId | Fra dato   | Til dato   | Opphørsdato | Beløp | Første utbetaling sak | Er endring | Periode id | Forrige periode id | Satstype |
      | 1            | 01.02.2021 | 31.03.2021 |             | 700   | Ja                    | Nei        | 0          |                    | MÅNEDLIG |
      | 2            | 01.04.2021 | 31.05.2021 |             | 900   | Nei                   | Nei        | 1          | 0                  | MÅNEDLIG |
