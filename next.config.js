const withMDX = require("@next/mdx")()

/** @type {import('next').NextConfig} */
const nextConfig = {
  output: "export",
  pageExtensions: ["js", "jsx", "mdx", "ts", "tsx"],
}

global.appRoot = require("path").resolve(__dirname)

module.exports = withMDX(nextConfig)
