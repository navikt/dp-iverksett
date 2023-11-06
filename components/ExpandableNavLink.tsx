"use client"

import { useLayoutEffect, useState } from "react"
import Link from "next/link"
import { usePathname } from "next/navigation"
import { BodyShort, Button, HStack } from "@navikt/ds-react"
import { formaterNavn, NavLink } from "@/components/NavLink"
import { DirectoryStructure } from "@/app/directoryStructure"
import { MenuLink } from "@/components/MenuLink"
import { ChevronDownIcon, ChevronUpIcon } from "@navikt/aksel-icons"

import styles from "@/components/ExpandableNavLink.module.css"

type Props = {
  name: string
  subDirectories: DirectoryStructure
  href: string
}

export function ExpandableNavLink({ name, subDirectories, href }: Props) {
  const [showLinks, setShowLinks] = useState(false)
  const pathname = usePathname()

  useLayoutEffect(() => {
    setShowLinks(pathname.includes(href))
  }, [href, pathname])

  const toggleShowLinks = (event: React.MouseEvent) => {
    event.preventDefault()
    setShowLinks((prevState) => !prevState)
  }

  return (
    <li key={name} className={styles.listItem}>
      <HStack wrap={false} className={styles.linkAndButton}>
        <Link href={href} legacyBehavior>
          <MenuLink>
            <BodyShort>{formaterNavn(name)}</BodyShort>
          </MenuLink>
        </Link>
        <Button
          onClick={toggleShowLinks}
          variant="tertiary-neutral"
          size="xsmall"
        >
          {showLinks ? <ChevronUpIcon /> : <ChevronDownIcon />}
        </Button>
      </HStack>
      {showLinks && (
        <ul className={styles.links}>
          {Object.entries(subDirectories).map(([name, subDirectories]) => (
            <NavLink
              key={name}
              name={name}
              subDirectories={subDirectories}
              href={`${href}/${name}`}
            />
          ))}
        </ul>
      )}
    </li>
  )
}
