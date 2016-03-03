(ns lein-java-practice.sudoku-adapter
  (:import [SudokuChecker])
  (:gen-class))

;There must be a better way to type this array
(defn to-java-2D-array [board]
  (into-array (type (int-array [1])) 
              (map int-array board)))


(defn check-board [board]
  (let [checker (SudokuChecker.)]
    (.CheckIsValid checker (to-java-2D-array board))))


