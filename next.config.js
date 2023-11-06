import createMDX from "@next/mdx"
import remarkGfm from "remark-gfm"
import mdxMermaid from "mdx-mermaid"
import rehypeSlug from "rehype-slug"

/** @type {import('next').NextConfig} */
const nextConfig = {
  output: "export",
  assetPrefix: "/dp-iverksett",
  basePath: "/dp-iverksett",
  pageExtensions: ["js", "jsx", "mdx", "ts", "tsx"],
  experimental: {
    webpackBuildWorker: true,
  },
}

const withMDX = createMDX({
  options: {
    remarkPlugins: [remarkGfm, mdxMermaid],
    rehypePlugins: [rehypeSlug],
  },
})

export default withMDX(nextConfig)