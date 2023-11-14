import { BodyShort, Heading, VStack } from "@navikt/ds-react"
import { FileXMarkIcon } from "@navikt/aksel-icons"

export default function NotFound() {
  return (
    <VStack gap="6">
      <Heading level="1" size="xlarge">
        <FileXMarkIcon /> Oisann, her var det ingenting
      </Heading>
      <BodyShort>Vi fant ikke siden du lette etter.</BodyShort>
    </VStack>
  )
}
