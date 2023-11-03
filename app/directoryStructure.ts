import fs from "fs"
import path from "path"

function getBasePath() {
  const rootDir = "dp-iverksett"
  let pathComponents = __dirname.split("/")
  while (pathComponents[pathComponents.length - 1] !== rootDir) {
    pathComponents = pathComponents.slice(0, pathComponents.length - 1)
  }
  return pathComponents.concat("app").join("/")
}

export type DirectoryStructure = {
  [directoryName: string]: DirectoryStructure
}

export function buildDirectoryStructure(
  basePath: string = getBasePath(),
  ignore: string[] = [],
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

  return traverseDir(basePath)
}
