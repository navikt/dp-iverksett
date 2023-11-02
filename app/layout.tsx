import type { Metadata } from "next"
import { Inter } from "next/font/google"
import "./globals.css"
import { Heading, HGrid, Link, VStack } from "@navikt/ds-react"
import { Nav } from "@/app/Nav"
import clsx from "clsx"

import styles from "./layout.module.css"

const inter = Inter({ subsets: ["latin"] })

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
      <body className={clsx(inter.className, styles.body)}>
        <HGrid columns="max-content auto" className={styles.container}>
          <section className={styles.sideBar}>
            <VStack gap="6">
              <Link href="/">
                <Heading size="medium">dp-iverksett</Heading>
              </Link>
              <Nav />
            </VStack>
          </section>
          <main className={styles.mainContent}>{children}</main>
        </HGrid>
      </body>
    </html>
  )
}
