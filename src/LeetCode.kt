fun main() {
    val a = productExceptSelf(intArrayOf(1, 2, 3, 4))
    println(a)
}

fun productExceptSelf(nums: IntArray): IntArray {
    if (nums.isEmpty()) return nums

    val left = IntArray(nums.size)
    val right = IntArray(nums.size)
    val product = IntArray(nums.size)

    left[0] = 1
    for (i in 1 until nums.size) {
        left[i] = left[i - 1] * nums[i - 1]
    }

    right[nums.size - 1] = 1
    for (i in nums.size - 2 downTo 0) {
        right[i] = right[i + 1] * nums[i + 1]
    }

    for (i in nums.indices) {
        product[i] = left[i] * right[i]
    }

    return product
}
