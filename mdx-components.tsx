import type { MDXComponents } from "mdx/types"
import { BodyLong, Heading } from "@navikt/ds-react"

export function useMDXComponents(components: MDXComponents): MDXComponents {
  return {
    h1: ({ children }) => (
      <Heading level="1" size="large">
        {children}
      </Heading>
    ),
    h2: ({ children }) => (
      <Heading level="2" size="large">
        {children}
      </Heading>
    ),
    h3: ({ children }) => (
      <Heading level="3" size="large">
        {children}
      </Heading>
    ),
    p: ({ children }) => <BodyLong>{children}</BodyLong>,
    ...components,
  }
}
