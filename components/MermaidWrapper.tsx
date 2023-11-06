"use client"

import { useEffect } from "react"
import mermaid from "mermaid"

mermaid.initialize({
  startOnLoad: true,
})

type Props = {
  chart: string
}

export function MermaidWrapper({ chart }: Props) {
  useEffect(() => mermaid.contentLoaded(), [])

  return <div className="mermaid">{chart}</div>
}

export default MermaidWrapper
