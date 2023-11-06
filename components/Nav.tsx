import { buildDirectoryStructure } from "@/app/directoryStructure"
import { NavLink } from "@/components/NavLink"

import styles from "./Nav.module.css"

export function Nav() {
  return (
    <nav className={styles.nav}>
      <ul>
        {Object.entries(buildDirectoryStructure()).map(
          ([name, subDirectories]) => (
            <NavLink
              key={name}
              name={name}
              subDirectories={subDirectories}
              href={`/${name}`}
            />
          ),
        )}
      </ul>
    </nav>
  )
}
