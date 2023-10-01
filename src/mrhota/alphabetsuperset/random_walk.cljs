(ns mrhota.alphabetsuperset.random-walk)

;; think of this as an associative structure
;; so that we can (assoc-in grid [x y] :floor)
;; Here's what this vector might look like:
;; [[:wall :wall :wall :wall :wall]
;;  [:wall :floor :floor :floor :wall]
;;  [:wall :floor :floor :floor :wall]
;;  [:wall :floor :floor :floor :wall]
;;  [:wall :wall :wall :wall :wall]]
;; and we can update the :floor at [1 2] to :wall
;; using (assoc-in grid [1 2] :wall)
(defn create-grid [w h]
  (vec (repeat h (vec (repeat w :wall)))))

(defn- in-bounds? [pos w h]
  (and (>= (first pos) 0)
       (< (first pos) h)
       (>= (second pos) 0)
       (< (second pos) w)))

(defn random-walk [grid start steps]
  (loop [grid grid
         current start
         steps steps]
    (if (zero? steps)
      grid
      (let [direction (rand-nth [:up :down :left :right])
            next (case direction
                   :up [(- (first current) 1) (second current)]
                   :down [(+ (first current) 1) (second current)]
                   :left [(first current) (- (second current) 1)]
                   :right [(first current) (+ (second current) 1)])]
        (if (in-bounds? next (count (first grid)) (count grid))
          (let [updated-grid (assoc-in grid next :floor)]
            (recur updated-grid next (dec steps)))
          (recur grid current (dec steps)))))))