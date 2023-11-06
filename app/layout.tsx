import type { Metadata } from "next"
import { Source_Sans_3 } from "next/font/google"
import clsx from "clsx"
import { BodyShort, HGrid, Link } from "@navikt/ds-react"
import { TokenIcon } from "@navikt/aksel-icons"
import { Nav } from "@/app/Nav"
import "./globals.css"

import styles from "./layout.module.css"

const sourceSans = Source_Sans_3({
  subsets: ["latin"],
  display: "swap",
})

export const metadata: Metadata = {
  title: "DP-Iverksett",
  description: "Dokumentasjonsside for DP-Iverksett",
}

type Props = {
  children: React.ReactNode
}

export default function RootLayout({ children }: Props) {
  return (
    <html lang="en">
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
