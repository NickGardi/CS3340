#nicholas gardi - 250868721
import sys

size = int(sys.argv[1])

array = []
for i in range(size, 0, -1):
    array.append(i)


def insertionSort(array):

    for i in range(1, len(array)):
        key = array[i]
        j = i-1
        while j >= 0 and key < array[j]:
                array[j+1] = array[j]
                j -= 1
        array[j+1] = key

#OUTPUT
[print("array before sort: ")]
for i in range(0, 20):
    if i == 19:
         print(array[i])
    else:
        print(array[i], end=', ')
insertionSort(array)
print()
[print ("array after sort: ")]
for i in range(0, 20):
    if i == 19:
         print(array[i])
    else:
        print(array[i], end=', ')
