
// language=CSS
const themeCSS = `
p {
  font-size: 16px !important;
  margin: 0 0 12px !important;
}

.edge-thickness-normal {
  stroke-width: 2px;
}

.edge-thickness-thick {
  stroke-width: 3.5px;
}

.edge-pattern-solid {
  stroke-dasharray: 0;
}

.edge-pattern-dashed {
  stroke-dasharray: 3;
}

.edge-pattern-dotted {
  stroke-dasharray: 2;
}

.marker {
  fill: var(--a-text-default);
  stroke: var(--a-text-default);
}

.marker.cross {
  stroke: var(--a-text-default);
}

svg {
  font-family: arial, sans-serif;
  font-size: 16px;
}

.label {
  font-family: arial, sans-serif;
  color: var(--a-text-default);
}

.cluster-label text {
  fill: var(--a-text-default);
}

.cluster-label span, p {
  color: var(--a-text-default);
}

.label text, span, p {
  fill: var(--a-text-subtle);
  color: var(--a-text-subtle);
}

.node rect, .node circle, .node ellipse, .node polygon, .node path {
  fill: var(--mermaid-node-fill);
  stroke: var(--mermaid-node-stroke);
  stroke-width: 1px;
}

.flowchart-label text {
  text-anchor: middle;
}

.node .label {
  text-align: center;
}

.node.clickable {
  cursor: pointer;
}

.arrowheadPath {
  fill: var(--mermaid-line-color);
}

.edgePath .path {
  stroke: var(--mermaid-line-color);
  stroke-width: 2.0px;
}

.flowchart-link {
  stroke: var(--mermaid-line-color);
  fill: none;
}

.edgeLabels {
  transform: translateY(-2.5px);
}

.edgeLabel {
  background-color: var(--a-bg-default);
  text-align: center;
}

.edgeLabel rect {
  opacity: 0.5;
  background-color: var(--mermaid-node-fill);
  fill: var(--mermaid-node-fill);
}

.labelBkg {
  background-color: var(--mermaid-node-fill);
}

.cluster rect {
  fill: var(--mermaid-cluster-fill);
  stroke: var(--mermaid-cluster-stroke);
  stroke-width: 1px;
}

.cluster text {
  fill: var(--a-text-default);
}

.cluster span, p {
  color: var(--a-text-default);
}

div.mermaidTooltip {
  position: absolute;
  text-align: center;
  max-width: 200px;
  padding: 2px;
  font-family: arial, sans-serif;
  font-size: 12px;
  background: var(--mermaid-node-fill);
  border: 1px solid var(--mermaid-node-stroke);
  border-radius: 2px;
  pointer-events: none;
  z-index: 100;
}

.flowchartTitleText {
  text-anchor: middle;
  font-size: 18px;
  fill: var(--a-text-default);
}

:root {
  --mermaid-font-family: arial, sans-serif;
}
`

/** @type {import('mermaid').MermaidConfig} */
export const mermaidConfig = {
  theme: "base",
  themeCSS,
}