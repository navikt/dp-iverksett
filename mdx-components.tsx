import type { MDXComponents } from "mdx/types"
import { BodyLong, Heading } from "@navikt/ds-react"

import styles from "./mdx-components.module.css"
import dynamic from "next/dynamic"

const MermaidWrapper = dynamic(() => import("@/app/MermaidWrapper"), {
  ssr: false,
})

export function useMDXComponents(components: MDXComponents): MDXComponents {
  return {
    h1: ({ children }) => (
      <Heading level="1" size="large" className={styles.blockM}>
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
    p: ({ children }) => (
      <BodyLong className={styles.blockM}>{children}</BodyLong>
    ),
    mermaid: MermaidWrapper,
    ...components,
  }
}
