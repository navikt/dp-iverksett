import type { Metadata } from "next"
import Link from "next/link"
import Image from "next/image"
import { Source_Sans_3 } from "next/font/google"
import clsx from "clsx"
import { BodyShort, HGrid } from "@navikt/ds-react"
import { Nav } from "@/components/Nav"
import "./globals.css"

import navLogo from "@/public/nav-logo-red.svg"
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
          <Image src={navLogo.src} alt="" width="64" height="20" />
          <span className={styles.headerDivider} />
          <Link href="/" className={styles.headerLink}>
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
