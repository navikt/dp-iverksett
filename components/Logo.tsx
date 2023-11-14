import Image from "next/image"
import { BodyShort, HStack } from "@navikt/ds-react"

import navLogo from "@/public/nav-logo-red.svg"

import styles from "./Logo.module.css"

export const Logo = () => (
  <HStack gap="6" align="center">
    <Image src={navLogo.src} alt="" width="64" height="20" />
    <span className={styles.headerDivider} />
    <BodyShort weight="semibold" size="large">
      DP-Iverksett
    </BodyShort>
  </HStack>
)
