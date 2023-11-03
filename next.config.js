import createMDX from "@next/mdx"
import remarkGfm from "remark-gfm"
import mdxMermaid from "mdx-mermaid"

/** @type {import('next').NextConfig} */
const nextConfig = {
  output: "export",
  pageExtensions: ["js", "jsx", "mdx", "ts", "tsx"],
}

const withMDX = createMDX({
  options: {
    remarkPlugins: [remarkGfm, mdxMermaid],
  },
})

export default withMDX(nextConfig)
