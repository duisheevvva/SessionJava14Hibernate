package peaksoft.entity;


import jakarta.persistence.*;
import lombok.*;
import peaksoft.enums.StadyFormat;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_gen")
    @SequenceGenerator(name = "course_gen",
            sequenceName = "course_seq",
            allocationSize = 1)
    private Long id;
    @Column(name = "course_name")
    private String courseName;
    private int price;
    @Enumerated(EnumType.STRING)
    private StadyFormat stadyFormat;

    public Course(String courseName, int price, StadyFormat stadyFormat) {
        this.courseName = courseName;
        this.price = price;
        this.stadyFormat = stadyFormat;
    }
}
