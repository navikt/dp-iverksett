import fs from "fs"
import path from "path"

declare module global {
  let appRoot: string
}

const BASE_DIR = `${global.appRoot}/app`

export type DirectoryStructure = {
  [directoryName: string]: DirectoryStructure
}

export function buildDirectoryStructure(): DirectoryStructure {
  function traverseDir(currentDir: string): DirectoryStructure {
    const files: string[] = fs.readdirSync(currentDir)

    return files.reduce((structure: DirectoryStructure, file: string) => {
      const filePath: string = path.join(currentDir, file)
      const isDirectory: boolean = fs.statSync(filePath).isDirectory()

      if (isDirectory) {
        const directoryName: string = path.basename(filePath)
        structure[directoryName] = traverseDir(filePath)
      }

      return structure
    }, {})
  }

  return traverseDir(BASE_DIR)
}
