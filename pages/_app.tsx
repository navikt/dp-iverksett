import type { AppProps } from "next/app"
import { Source_Sans_3 } from "next/font/google"

import "./globals.css"

const sourceSans = Source_Sans_3({
  subsets: ["latin"],
  display: "swap",
})

export default function MyApp({ Component, pageProps }: AppProps) {
  return (
    <div className={sourceSans.className}>
      <Component {...pageProps} />
    </div>
  )
}
