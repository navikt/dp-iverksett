import { useConfig } from "nextra-theme-docs"
import { useRouter } from "next/router"

const getTitle = (frontMatter: { title: string }) =>
  `${frontMatter.title} - DP-Iverksett`

export const CustomHead = () => {
  const { asPath, defaultLocale, locale } = useRouter()
  const { frontMatter } = useConfig()
  const url =
    "https://navikt.github.io/dp-iverksett" +
    (defaultLocale === locale ? asPath : `/${locale}${asPath}`)

  return (
    <>
      <title>{getTitle(frontMatter)}</title>
      <meta property="og:url" content={url} />
      <meta
        property="og:title"
        content={`${frontMatter.title} - DP-Iverksett`}
      />
      <meta property="og:description" content={frontMatter.description} />
    </>
  )
}
