import withNextra from "nextra"

/** @type {import('next').NextConfig} */
const nextConfig = {
  output: "export",
  assetPrefix:
    process.env.NODE_ENV === "production" ? "/utsjekk" : undefined,
  basePath: process.env.NODE_ENV === "production" ? "/utsjekk" : undefined,
  images: {
    unoptimized: true,
  },
}

export default withNextra({
  theme: "nextra-theme-docs",
  themeConfig: "./theme.config.tsx",
  unstable_staticImage: false,
})(nextConfig)
