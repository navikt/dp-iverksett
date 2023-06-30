# language: no
# encoding: UTF-8

Egenskap: Vedtak for førstegangsbehandling


  Scenario: Vedtak med en periode

    Gitt følgende tilkjente ytelser
      | BehandlingId | Fra dato   | Til dato   | Beløp |
      | 1            | 01.03.2021 | 01.03.2021 | 700   |

    Når beregner utbetalingsoppdrag

    Så forvent følgende utbetalingsoppdrag 2
      | BehandlingId | Fra dato   | Til dato   | Opphørsdato | Beløp | Kode endring | Er endring | Periode id | Forrige periode id | Satstype |
      | 1            | 01.03.2021 | 01.03.2021 |             | 700   | NY           | Nei        | 0          |                    | MND      |


  Scenario: Revurdering uten endring av andeler

    Gitt følgende tilkjente ytelser
      | BehandlingId | Fra dato   | Til dato   | Beløp |
      | 1            | 01.03.2021 | 01.03.2021 | 700   |
      | 1            | 01.04.2021 | 01.04.2021 | 700   |
      | 2            | 01.03.2021 | 01.03.2021 | 700   |
      | 2            | 01.04.2021 | 01.04.2021 | 700   |

    Når beregner utbetalingsoppdrag

    Så forvent følgende utbetalingsoppdrag 2
      | BehandlingId | Fra dato   | Til dato   | Opphørsdato | Beløp | Kode endring | Er endring | Periode id | Forrige periode id |
      | 1            | 01.03.2021 | 01.03.2021 |             | 700   | NY           | Nei        | 0          |                    |
      | 1            | 01.04.2021 | 01.04.2021 |             | 700   | NY           | Nei        | 1          | 0                  |


  Scenario: Vedtak med to perioder

    Gitt følgende tilkjente ytelser
      | BehandlingId | Fra dato   | Til dato   | Beløp |
      | 1            | 01.03.2021 | 01.03.2021 | 700   |
      | 1            | 01.04.2021 | 01.05.2021 | 800   |

    Når beregner utbetalingsoppdrag

    Så forvent følgende utbetalingsoppdrag 2
      | BehandlingId | Fra dato   | Til dato   | Opphørsdato | Beløp | Kode endring | Er endring | Periode id | Forrige periode id |
      | 1            | 01.03.2021 | 01.03.2021 |             | 700   | NY           | Nei        | 0          |                    |
      | 1            | 01.04.2021 | 01.05.2021 |             | 800   | NY           | Nei        | 1          | 0                  |


  Scenario: Revurdering som legger til en periode, simulering skal opphøre fra start for å kunne vise all historikk

    Gitt følgende tilkjente ytelser
      | BehandlingId | Fra dato   | Til dato   | Beløp |
      | 1            | 01.03.2021 | 01.03.2021 | 700   |
      | 2            | 01.03.2021 | 01.03.2021 | 700   |
      | 2            | 01.04.2021 | 01.04.2021 | 800   |

    Når beregner utbetalingsoppdrag

    Så forvent følgende utbetalingsoppdrag 2
      | BehandlingId | Fra dato   | Til dato   | Opphørsdato | Beløp | Kode endring | Er endring | Periode id | Forrige periode id |
      | 1            | 01.03.2021 | 01.03.2021 |             | 700   | NY           | Nei        | 0          |                    |
      | 2            | 01.04.2021 | 01.04.2021 |             | 800   | ENDR         | Nei        | 1          | 0                  |


  Scenario: 2 revurderinger som legger til en periode

    Gitt følgende tilkjente ytelser
      | BehandlingId | Fra dato   | Til dato   | Beløp |
      | 1            | 01.03.2021 | 01.03.2021 | 700   |
      | 2            | 01.03.2021 | 01.03.2021 | 700   |
      | 2            | 01.04.2021 | 01.04.2021 | 800   |
      | 3            | 01.03.2021 | 01.03.2021 | 700   |
      | 3            | 01.04.2021 | 01.04.2021 | 800   |
      | 3            | 01.05.2021 | 01.05.2021 | 900   |

    Når beregner utbetalingsoppdrag

    Så forvent følgende utbetalingsoppdrag 2
      | BehandlingId | Fra dato   | Til dato   | Opphørsdato | Beløp | Kode endring | Er endring | Periode id | Forrige periode id | Kildebehandling |
      | 1            | 01.03.2021 | 01.03.2021 |             | 700   | NY           | Nei        | 0          |                    | 1               |
      | 2            | 01.04.2021 | 01.04.2021 |             | 800   | ENDR         | Nei        | 1          | 0                  | 2               |
      | 3            | 01.05.2021 | 01.05.2021 |             | 900   | ENDR         | Nei        | 2          | 1                  | 3               |


  Scenario: Endrer beløp fra april

    Gitt følgende tilkjente ytelser
      | BehandlingId | Fra dato   | Til dato   | Beløp |
      | 1            | 01.03.2021 | 01.06.2021 | 700   |
      | 2            | 01.03.2021 | 01.03.2021 | 700   |
      | 2            | 01.04.2021 | 01.06.2021 | 800   |

    Når beregner utbetalingsoppdrag

    Så forvent følgende utbetalingsoppdrag 2
      | BehandlingId | Fra dato   | Til dato   | Opphørsdato | Beløp | Kode endring | Er endring | Periode id | Forrige periode id | Kildebehandling |
      | 1            | 01.03.2021 | 01.06.2021 |             | 700   | NY           | Nei        | 0          |                    | 1               |
      | 2            | 01.04.2021 | 01.06.2021 |             | 800   | ENDR         | Nei        | 1          | 0                  | 2               |


  Scenario: Første perioden blir avkortet, og den andre er lik

    Gitt følgende tilkjente ytelser
      | BehandlingId | Fra dato   | Til dato   | Beløp |
      | 1            | 01.03.2021 | 01.04.2021 | 700   |
      | 1            | 01.05.2021 | 07.2021    | 700   |
      | 2            | 01.03.2021 | 01.03.2021 | 700   |
      | 2            | 01.05.2021 | 07.2021    | 700   |

    Når beregner utbetalingsoppdrag

    Så forvent følgende utbetalingsoppdrag 2
      | BehandlingId | Fra dato   | Til dato   | Opphørsdato | Beløp | Kode endring | Er endring | Periode id | Forrige periode id | Kildebehandling |
      | 1            | 01.03.2021 | 01.04.2021 |             | 700   | NY           | Nei        | 0          |                    | 1               |
      | 1            | 01.05.2021 | 07.2021    |             | 700   | NY           | Nei        | 1          | 0                  | 1               |
      | 2            | 01.05.2021 | 07.2021    | 01.04.2021  | 700   | ENDR         | Ja         | 1          | 0                  | 2               |
      | 2            | 01.05.2021 | 07.2021    |             | 700   | ENDR         | Nei        | 2          | 1                  | 2               |

  Scenario: Endrer beløp fra start

    Gitt følgende tilkjente ytelser
      | BehandlingId | Fra dato   | Til dato   | Beløp |
      | 1            | 01.03.2021 | 01.06.2021 | 700   |
      | 2            | 01.03.2021 | 01.03.2021 | 800   |
      | 2            | 01.04.2021 | 01.06.2021 | 700   |

    Når beregner utbetalingsoppdrag

    Så forvent følgende utbetalingsoppdrag 2
      | BehandlingId | Fra dato   | Til dato   | Opphørsdato | Beløp | Kode endring | Er endring | Periode id | Forrige periode id |
      | 1            | 01.03.2021 | 01.06.2021 |             | 700   | NY           | Nei        | 0          |                    |
      | 2            | 01.03.2021 | 01.03.2021 |             | 800   | ENDR         | Nei        | 1          | 0                  |
      | 2            | 01.04.2021 | 01.06.2021 |             | 700   | ENDR         | Nei        | 2          | 1                  |

    Så forvent følgende andeler med periodeId
      | BehandlingId | Id | Periode id | Forrige periode id | Kildebehandling |
      | 1            | 0  | 0          |                    | 1               |
      | 2            | 1  | 1          | 0                  | 2               |
      | 2            | 2  | 2          | 1                  | 2               |

  Scenario: Opphør alle perioder for å sen iverksette på nytt, verifiserer at man fortsatt sender ENDR

    Gitt følgende tilkjente ytelser
      | BehandlingId | Uten andeler | Fra dato   | Til dato   | Beløp |
      | 1            |              | 01.03.2021 | 01.03.2021 | 700   |
      | 2            | Ja           |            |            |       |
      | 3            |              | 01.03.2021 | 01.03.2021 | 700   |

    Når beregner utbetalingsoppdrag

    Så forvent følgende utbetalingsoppdrag 2
      | BehandlingId | Fra dato   | Til dato   | Opphørsdato | Beløp | Kode endring | Er endring | Periode id | Forrige periode id |
      | 1            | 01.03.2021 | 01.03.2021 |             | 700   | NY           | Nei        | 0          |                    |
      | 2            | 01.03.2021 | 01.03.2021 | 01.03.2021  | 700   | ENDR         | Ja         | 0          |                    |
      | 3            | 01.03.2021 | 01.03.2021 |             | 700   | ENDR         | Nei        | 1          | 0                  |

  Scenario: Skolepenger skal bruke engangsutbetaling

    Gitt følgende behandlingsinformasjon
      | BehandlingId | Ytelse      |
      | 1            | SKOLEPENGER |

    Gitt følgende tilkjente ytelser
      | BehandlingId | Fra dato   | Til dato   | Beløp | Ytelse      |
      | 1            | 01.03.2021 | 01.03.2021 | 700   | SKOLEPENGER |

    Når beregner utbetalingsoppdrag

    Så forvent følgende utbetalingsoppdrag 2
      | BehandlingId | Fra dato   | Til dato   | Opphørsdato | Beløp | Kode endring | Er endring | Periode id | Forrige periode id | Satstype | Ytelse      |
      | 1            | 01.03.2021 | 01.03.2021 |             | 700   | NY           | Nei        | 0          |                    | ENG      | SKOLEPENGER |
