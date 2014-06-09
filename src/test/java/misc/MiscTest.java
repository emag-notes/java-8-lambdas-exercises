package misc;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.SampleData;
import com.insightfullogic.java8.examples.chapter1.Track;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author tanabe
 */
public class MiscTest {

  @Test
  public void flatMapTest() throws Exception {
    // Setup
    // Exercise
    List<Integer> together = Stream.of(asList(1, 2), asList(3, 4))
      .flatMap(numbers -> numbers.stream())
      .collect(toList());

    // Verify
    assertThat(together, is(asList(1, 2, 3, 4)));
  }

  @Test
  public void Chapter3RefactorStep3Anatomy() throws Exception {
    // Setup
    List<Album> albums = unmodifiableList(asList(SampleData.aLoveSupreme, SampleData.sampleShortAlbum));

    // Exercise
    Set<String> tackNames = new HashSet<>();

    List<Track> tracks = albums.stream()
      .flatMap(album -> album.getTracks())
      .collect(toList());

    // Verify
    System.out.println(tracks);
  }
  
  @Test
  public void printMethods() throws Exception {
    // Setup
    Stream<Class<?>> stream = Stream.of(Set.class, List.class, Map.class);
    // Exercise
    // Verify
    stream.flatMap(clazz -> Arrays.stream(clazz.getMethods()))
      .map(method -> method.getName())
      .distinct()
      .sorted()
      .forEach(System.out::println);
  }

  @Test
  public void sentencesSplitterStep0() throws Exception {
    // Setup
    List<String> sentences = asList("I am a Java lover", "Also WildFly lover");
    // Exercise
    // Verify
    sentences.stream()
      .map(s -> s.split(" "))
      .forEach(words -> {
        Arrays.stream(words)
          .forEach(System.out::println);
      });
  }

  @Test
  public void sentencesSplitterStep1() throws Exception {
    // Setup
    List<String> sentences = asList("I am a Java lover", "Also WildFly lover");
    // Exercise
    // Verify
    sentences.stream()
      .flatMap(s -> Arrays.stream(s.split(" ")))
//      .distinct()
      .forEach(System.out::println);
  }

  @Test
  public void Collectors_toListTest() throws Exception {
    // Setup
    // Exercise
    List<Integer> odds = IntStream.iterate(0, n -> n + 1)
      .limit(10)
      .mapToObj(n -> n * 2)
      .collect(Collectors.toList());
    // Verify
    System.out.println(odds);
  }

  @Test
  public void Collectors_joiningTest() throws Exception {
    List<String> texts = Arrays.asList("a", "b", "c");

    // 要素を単純に連結
    String joinedText1 = texts.stream()
      .collect(Collectors.joining());
    System.out.println(joinedText1); // abc

    // 要素間の文字列を指定
    String joinedText2 = texts.stream()
      .collect(Collectors.joining(":"));
    System.out.println(joinedText2); // a:b:c

    // 要素間の文字列に加え、最初と最後の文字列を指定
    String joinedText3 = texts.stream()
      .collect(Collectors.joining(", ", "[", "]"));
    System.out.println(joinedText3); // [a, b, c]
  }

  @Test
  public void Collectors_groupingByTest() throws Exception {
    // Setup
    List<String> words = Arrays.asList("alpha", "beta", "apple", "bridge", "application");

    // Exercise
    Map<String, List<String>> groups1
      = words.stream()
      .collect(Collectors.groupingBy(w -> w.substring(0, 1)));

    // Verify
    System.out.println(groups1);

    Map<String, Long> groups2
      = words.stream()
      .collect(Collectors.groupingBy(
        w -> w.substring(0, 1),
        Collectors.counting()));

    // Verify
    System.out.println(groups2);
  }
}
