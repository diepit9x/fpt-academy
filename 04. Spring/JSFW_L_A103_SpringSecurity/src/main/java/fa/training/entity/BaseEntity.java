package fa.training.entity;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {
    private LocalDateTime createDate;
    private LocalDateTime updateTime;

    @PrePersist
    protected void onCreateDate() {
	createDate = LocalDateTime.now();
	updateTime = LocalDateTime.now();
    }

    @PreUpdate
    protected void OnUpdateDate() {
	updateTime = LocalDateTime.now();
    }
}
