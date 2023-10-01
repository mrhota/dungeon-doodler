(ns mrhota.alphabetsuperset.d
  (:require [mrhota.alphabetsuperset.random-walk :as rw]
            [quil.core :as q :include-macros true]
            [quil.helpers.seqs :as qseqs]
            [quil.helpers.drawing :as qdraw]
            [quil.helpers.calc :as qcalc]))

(defn draw-grid [grid cell-size]
  (doseq [y (range (count grid))
          x (range (count (first grid)))]
    (let [cell (get-in grid [y x])]
      (q/fill (case cell
                :wall [80 80 80]
                :floor [130 130 130]))
      (q/rect (* x cell-size) (* y cell-size) cell-size cell-size))))

(defn setup []
  (q/stroke 35)
  (q/stroke-weight 1)

  (q/frame-rate 1))

(defn draw []
  (let [cell-size 10
        start-point [50 50]
        width (/ (q/width) cell-size)
        height (/ (q/height) cell-size)
        starting-grid (rw/create-grid width height)
        grid (rw/random-walk starting-grid start-point 1000)]
    (draw-grid grid cell-size)))

;; clj-kondo seems to have a problem with this macro, and
;; yells at me that dungeon-doodle is an unresolved symbol.
;; I'm not sure how to fix this, so I'm just going to ignore
#_{:clj-kondo/ignore [:unresolved-symbol]}
(q/defsketch dungeon-doodle
  :host "app"
  :setup setup
  :draw draw
  :size [800 600])
