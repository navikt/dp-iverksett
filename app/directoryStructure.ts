import fs from "fs"
import path from "path"

const BASE_DIR = path.join(__dirname, "../app")

export type DirectoryStructure = {
  [directoryName: string]: DirectoryStructure
}

export function buildDirectoryStructure(
  ignore: string[] = ["favicon.ico"],
): DirectoryStructure {
  function traverseDir(currentDir: string): DirectoryStructure {
    const files: string[] = fs.readdirSync(currentDir)

    return files.reduce((structure: DirectoryStructure, file: string) => {
      const filePath: string = path.join(currentDir, file)
      const isDirectory: boolean = fs.statSync(filePath).isDirectory()

      if (isDirectory) {
        const directoryName: string = path.basename(filePath)
        if (!ignore.includes(directoryName)) {
          structure[directoryName] = traverseDir(filePath)
        }
      }

      return structure
    }, {})
  }

  return traverseDir(BASE_DIR)
}
