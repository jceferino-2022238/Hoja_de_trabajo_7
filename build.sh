
set -e

SRC_MAIN="src/main/java"
SRC_TEST="src/test/java"
OUT="out"

clean() {
  rm -rf "$OUT"
  echo "Cleaned."
}

compile_main() {
  mkdir -p "$OUT/main"
  javac -encoding UTF-8 \
        -sourcepath "$SRC_MAIN" \
        -d "$OUT/main" \
        "$SRC_MAIN/model/Association.java" \
        "$SRC_MAIN/model/BSTNode.java" \
        "$SRC_MAIN/model/BinaryTree.java" \
        "$SRC_MAIN/model/Dictionary.java" \
        "$SRC_MAIN/model/FileLoader.java" \
        "$SRC_MAIN/model/Translator.java" \
        "$SRC_MAIN/model/Profiler.java" \
        "$SRC_MAIN/controller/DictionaryController.java" \
        "$SRC_MAIN/view/ConsoleView.java" \
        "$SRC_MAIN/Main.java"
  echo "Main compiled."
}

compile_test() {
  mkdir -p "$OUT/test"
  javac -encoding UTF-8 \
        -cp "$OUT/main" \
        -d "$OUT/test" \
        "$SRC_TEST/BinaryTreeTest.java"
  echo "Tests compiled."
}

run_main() {
  java -cp "$OUT/main" Main
}

run_tests() {
  java -cp "$OUT/main:$OUT/test" test.BinaryTreeTest
}

case "${1:-run}" in
  clean)   clean ;;
  test)    compile_main && compile_test && run_tests ;;
  compile) compile_main ;;
  run|*)   compile_main && run_main ;;
esac
