"use client"

import { useEffect } from "react"
import mermaid from "mermaid"

const prefersDarkMode = () => {
  return (
    window.matchMedia &&
    window.matchMedia("(prefers-color-scheme: dark)").matches
  )
}

mermaid.initialize({
  startOnLoad: true,
  theme: prefersDarkMode() ? "dark" : "default",
})

type Props = {
  chart: string
}

export function MermaidWrapper({ chart }: Props) {
  useEffect(() => mermaid.contentLoaded(), [])

  return <div className="mermaid">{chart}</div>
}

export default MermaidWrapper
