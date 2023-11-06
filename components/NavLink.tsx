import { BodyShort } from "@navikt/ds-react"
import Link from "next/link"
import { DirectoryStructure } from "@/app/directoryStructure"
import { ExpandableNavLink } from "@/components/ExpandableNavLink"
import { MenuLink } from "@/components/MenuLink"

export function formaterNavn(uformatert: string): string {
  return uformatert[0].toUpperCase() + uformatert.slice(1).replace("_", " ")
}

type Props = {
  name: string
  subDirectories: DirectoryStructure
  href: string
}

export function NavLink({ name, subDirectories, href }: Props) {
  if (Object.keys(subDirectories).length > 0) {
    return (
      <ExpandableNavLink
        name={name}
        subDirectories={subDirectories}
        href={href}
      />
    )
  }

  return (
    <li key={name}>
      <Link href={href} legacyBehavior>
        <MenuLink>
          <BodyShort>{formaterNavn(name)}</BodyShort>
        </MenuLink>
      </Link>
    </li>
  )
}
