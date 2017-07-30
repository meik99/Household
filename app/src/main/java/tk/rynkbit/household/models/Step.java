package tk.rynkbit.household.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by michael on 30.07.17.
 */
@Entity
public class Step {
    @Id(autoincrement = true)
    private Long id;
    @NotNull
    private String name;
    @Generated(hash = 839953102)
    public Step(Long id, @NotNull String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 561308863)
    public Step() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
