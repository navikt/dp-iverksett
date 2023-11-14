import { Detail, HStack } from "@navikt/ds-react"
import React from "react"

import styles from "./IngenTreff.module.css"

export const IngenTreff = () => {
  return (
    <HStack className={styles.container} justify="center" align="center">
      <Detail>Ingen treff</Detail>
    </HStack>
  )
}
