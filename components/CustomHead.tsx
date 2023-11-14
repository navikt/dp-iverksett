import { useConfig } from "nextra-theme-docs"
import { useRouter } from "next/router"

export const CustomHead = () => {
  const { asPath, defaultLocale, locale } = useRouter()
  const { frontMatter } = useConfig()
  const url =
    "https://navikt.github.io/dp-iverksett" +
    (defaultLocale === locale ? asPath : `/${locale}${asPath}`)

  console.log(frontMatter)

  return (
    <>
      <title>{frontMatter.title} - DP-Iverksett</title>
      <meta property="og:url" content={url} />
      <meta
        property="og:title"
        content={`${frontMatter.title} - DP-Iverksett`}
      />
      <meta property="og:description" content={frontMatter.description} />
    </>
  )
}
