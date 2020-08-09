package ps_project.appointment;

/**
 * Abstract class defining the Observer's responsibility (update function)
 */
public abstract class Observer {
    Subject subject;
    public abstract void update();
}
