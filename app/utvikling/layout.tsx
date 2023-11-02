import styles from "./layout.module.css"

type Props = {
  children: React.ReactNode
}

export default function PageLayout({ children }: Props) {
  return <section className={styles.layout}>{children}</section>
}
