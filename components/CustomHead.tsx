import { useConfig } from "nextra-theme-docs"
import { useRouter } from "next/router"

const getTitle = (frontMatter: { title: string }) =>
  `${frontMatter.title} - utsjekk`

export const CustomHead = () => {
  const { asPath, defaultLocale, locale } = useRouter()
  const { frontMatter } = useConfig()
  const url =
    "https://navikt.github.io/utsjekk" +
    (defaultLocale === locale ? asPath : `/${locale}${asPath}`)

  return (
    <>
      <title>{getTitle(frontMatter)}</title>
      <meta property="og:url" content={url} />
      <meta property="og:title" content={`${frontMatter.title} - utsjekk`} />
      <meta property="og:description" content={frontMatter.description} />
    </>
  )
}
