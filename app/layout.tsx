import type { Metadata } from "next"
import "./globals.css"
import { BodyShort, HGrid, Link } from "@navikt/ds-react"
import { Nav } from "@/app/Nav"

import { Source_Sans_3 } from "next/font/google"

const sourceSans = Source_Sans_3({
  subsets: ["latin"],
  display: "swap",
})

import styles from "./layout.module.css"
import { TokenIcon } from "@navikt/aksel-icons"
import Head from "next/head"
import clsx from "clsx"

export const metadata: Metadata = {
  title: "dp-iverksett",
  description: "Dokumentasjonsside for dp-iverksett",
}

type Props = {
  children: React.ReactNode
}

export default function RootLayout({ children }: Props) {
  return (
    <html lang="en">
      <Head>
        <link
          rel="preload"
          href="https://cdn.nav.no/aksel/fonts/SourceSans3-normal.woff2"
          as="font"
          type="font/woff2"
        />
        <link
          rel="preload"
          href={"https://cdn.nav.no/aksel/@navikt/ds-css/2.9.0/index.min.css"}
          as="style"
        ></link>
      </Head>
      <body className={clsx(sourceSans.className, styles.body)}>
        <header className={styles.header}>
          <Link href="/" className={styles.link}>
            <TokenIcon fontWeight="semibold" fontSize="32" />
            <BodyShort weight="semibold" size="large">
              DP-Iverksett
            </BodyShort>
          </Link>
        </header>
        <HGrid columns="max-content auto" className={styles.container}>
          <section className={styles.sideBar}>
            <Nav />
          </section>
          <main className={styles.mainContent}>{children}</main>
        </HGrid>
      </body>
    </html>
  )
}
