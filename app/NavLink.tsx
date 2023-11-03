import { BodyShort, Link } from "@navikt/ds-react"
import { DirectoryStructure } from "@/app/directoryStructure"

import styles from "./NavLink.module.css"

function formaterNavn(uformatert: string): string {
  return uformatert[0].toUpperCase() + uformatert.slice(1).replace("_", " ")
}

type Props = {
  name: string
  subDirectories: DirectoryStructure
  href: string
}

export function NavLink({ name, subDirectories, href }: Props) {
  return (
    <li key={name} className={styles.listItem}>
      <Link href={href} className={styles.link}>
        <BodyShort>{formaterNavn(name)}</BodyShort>
      </Link>
      {Object.keys(subDirectories).length > 0 && (
        <ul>
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
