import React from "react"
import { ExternalLinkIcon } from "@navikt/aksel-icons"
import { Detail } from "@navikt/ds-react"

import styles from "./ExternalLabel.module.css"

type Props = {
  children: React.ReactNode
}

export const ExternalLabel: React.FC<Props> = ({ children }) => (
  <Detail className={styles.label}>
    {children} <ExternalLinkIcon />
  </Detail>
)
