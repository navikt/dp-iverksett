import { BodyShort, Heading, VStack } from "@navikt/ds-react"
import { FileXMarkIcon } from "@navikt/aksel-icons"

import styles from "./not-found.module.css"

export default function NotFound() {
  return (
    <VStack gap="6">
      <Heading level="1" size="xlarge" className={styles.heading}>
        <FileXMarkIcon /> Oisann, her var det ingenting
      </Heading>
      <BodyShort>Vi fant ikke siden du lette etter.</BodyShort>
    </VStack>
  )
}
