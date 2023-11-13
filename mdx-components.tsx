import type { MDXComponents } from "mdx/types"
import { BodyLong, Heading } from "@navikt/ds-react"

import styles from "./mdx-components.module.css"

export function useMDXComponents(components: MDXComponents): MDXComponents {
  return {
    h1: ({ children, ...rest }) => (
      <Heading
        level="1"
        size="large"
        className={styles.blockM}
        {...(rest as any)}
      >
        {children}
      </Heading>
    ),
    h2: ({ children, ...rest }) => (
      <Heading level="2" size="medium" {...(rest as any)}>
        {children}
      </Heading>
    ),
    h3: ({ children, ...rest }) => (
      <Heading level="3" size="small" {...(rest as any)}>
        {children}
      </Heading>
    ),
    p: ({ children }) => (
      <BodyLong className={styles.blockM}>{children}</BodyLong>
    ),
    ...components,
  }
}