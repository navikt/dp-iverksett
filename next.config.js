import createMDX from "@next/mdx"
import remarkGfm from "remark-gfm"
import rehypeSlug from "rehype-slug"
import rehypeMermaid from "rehype-mermaid";

/** @type {import('next').NextConfig} */
const nextConfig = {
  output: "export",
  assetPrefix:
    process.env.NODE_ENV === "production" ? "/dp-iverksett" : undefined,
  basePath: process.env.NODE_ENV === "production" ? "/dp-iverksett" : undefined,
  pageExtensions: ["js", "jsx", "mdx", "ts", "tsx"],
  experimental: {
    webpackBuildWorker: true,
  },
}

const withMDX = createMDX({
  options: {
    remarkPlugins: [remarkGfm],
    rehypePlugins: [rehypeSlug, [rehypeMermaid, { strategy: "inline-svg" }]],
  },
})

export default withMDX(nextConfig)
