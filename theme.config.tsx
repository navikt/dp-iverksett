import React from "react"
import Link from "next/link"
import { DocsThemeConfig } from "nextra-theme-docs"
import { Detail } from "@navikt/ds-react"

import { Logo } from "@/components/Logo"
import { IngenTreff } from "@/components/IngenTreff"
import { ExternalLabel } from "@/components/ExternalLabel"
import { CustomHead } from "@/components/CustomHead"

const config: DocsThemeConfig = {
  logo: <Logo />,
  project: {
    link: "https://github.com/navikt/utsjekk",
  },
  head: CustomHead,
  docsRepositoryBase: "https://github.com/navikt/utsjekk",
  footer: {
    text: "© 2023 NAV IT",
  },
  feedback: {
    content: <ExternalLabel>Gi oss tilbakemelding</ExternalLabel>,
  },
  editLink: {
    text: <Detail>Rediger denne siden</Detail>,
    component: ({ className, children, filePath }) => (
      <Link
        className={className}
        href={`https://github.com/navikt/utsjekk/blob/docs/${filePath}`}
      >
        {children}
      </Link>
    ),
  },
  toc: {
    title: (
      <span style={{ color: "var(--a-text-default" }}>På denne siden</span>
    ),
  },
  search: {
    placeholder: "Søk i dokumentasjonen",
    emptyResult: <IngenTreff />,
  },
}

export default config
