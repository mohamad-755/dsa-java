class Node
{
	int data;
	Node left, right;

	Node(int key)
	{
		data = key;
		left = right = null;
	}

    Node(int key, Node l, Node r)
	{
		data = key;
		left = l;
        right = r;
	}
}
