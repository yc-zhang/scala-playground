val dogBreeds = List("Doberman", "Yorkshire", "Dachshund", "Scottish")

for (breed <- dogBreeds)
  println(breed)

dogBreeds.foreach(s => println(s))

dogBreeds.foreach(println)

dogBreeds foreach println

for ( i <- 1 to 10) println(i)

for (bread <- dogBreeds if bread.contains("Y"))
  println(bread)

for (bread <- dogBreeds if bread.contains("Y") || bread.contains("S"))
  println(bread)

for {
  breed <- dogBreeds
  upperBreed = breed.toUpperCase()
} println(upperBreed)
