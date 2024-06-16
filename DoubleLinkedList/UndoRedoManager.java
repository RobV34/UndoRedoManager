package DoubleLinkedList;

/**
 * Undo/Redo Functionality: In applications that support undo/redo functionality, a doubly linked list can be used to
 * maintain a sequence of states. Each state change is stored as a node in the list, allowing easy navigation between
 * previous and next states, enabling undoing and redoing of actions.
 */
public class UndoRedoManager<T> {
    // Inner class representing a node in the doubly linked list
    private class Node {
        private T state;   // State stored in the node
        private Node prev; // Reference to the previous node
        private Node next; // Reference to the next node

        // Constructor to create a new node with a given state
        private Node(T state) {
            this.state = state;
        }
    }

    private Node currentState; // Reference to the current state in the list

    // Undo the current state and move to the previous state
    public T undo() {
        if (currentState == null) {
            // If there's no state to undo
            System.out.println("No state to undo");
            return null;
        }

        // Get the previous state
        Node previousState = currentState.prev;
        if (previousState == null) {
            // If already at the initial state
            System.out.println("Reached the initial state");
            return null;
        } else {
            // Update the current state to the previous state
            currentState = previousState;
        }
        return currentState.state; // Return the new current state
    }

    // Redo the current state and move to the next state
    public T redo() {
        if (currentState == null) {
            // If there's no state to redo
            System.out.println("No state to redo");
            return null;
        }

        // Get the next state
        Node nextState = currentState.next;
        if (nextState == null) {
            // If there's no state ahead to redo
            System.out.println("No state to redo");
            return null;
        } else {
            // Update the current state to the next state
            currentState = nextState;
        }
        return currentState.state; // Return the new current state
    }

    // Perform a new action and add it to the state sequence
    public void performAction(T newState) {
        // Create a new node for the new state
        Node newNode = new Node(newState);

        // Set the links for the new node
        newNode.prev = currentState;
        newNode.next = null;

        // Update the next link for the current state
        if (currentState != null) {
            currentState.next = newNode;
        }

        // Update the current state to the new state
        currentState = newNode;
    }

    public static void main(String[] args) {
        // Create an instance of UndoRedoManager for String states
        UndoRedoManager<String> undoRedoManager = new UndoRedoManager<>();

        // Perform several actions
        undoRedoManager.performAction("State 1");
        undoRedoManager.performAction("State 2");
        undoRedoManager.performAction("State 3");
        undoRedoManager.performAction("State 4");
        undoRedoManager.performAction("State 5");

        // Print the current state
        System.out.println("Current state: " + undoRedoManager.currentState.state);

        // Perform undo operations and print the current state after each undo
        undoRedoManager.undo();
        System.out.println("Current state after undo: " + undoRedoManager.currentState.state);
        undoRedoManager.undo();
        System.out.println("Current state after undo: " + undoRedoManager.currentState.state);

        // Perform redo operations and print the current state after each redo
        undoRedoManager.redo();
        System.out.println("Current state after redo: " + undoRedoManager.currentState.state);
        undoRedoManager.redo();
        System.out.println("Current state after redo: " + undoRedoManager.currentState.state);

        // Perform another undo operation and print the current state
        undoRedoManager.undo();
        System.out.println("Current state after undo: " + undoRedoManager.currentState.state);
    }
}


