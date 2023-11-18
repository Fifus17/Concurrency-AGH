package models

data class Node(val name: Char, val id: Int, val forward: List<Node?>)
