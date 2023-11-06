import React from "react"
import { Link, LinkProps } from "@navikt/ds-react"

import styles from "./MenuLink.module.css"

type Props = {
  children: React.ReactNode
} & LinkProps

export const MenuLink = React.forwardRef(
  ({ children, ...props }: Props, ref) => {
    return (
      <Link {...props} className={styles.link}>
        {children}
      </Link>
    )
  },
)

MenuLink.displayName = "MenuLink"
