# 【WIP】 Notion Shortcut for Android

---

> [!CAUTION]  
> 本リポジトリは個人的用途の為一時的に公開したものです。  
> 関係者以外の閲覧、使用はご遠慮ください。  
> 
> This repository is temporary public for personal use.  
> Please do not use it without permission.


## 概要
Notionへの投稿を簡単に行うためのAndroidアプリです。(開発途中)



## ディレクトリ構成
> [!NOTE]  
> Androidエンジニアの方は読み飛ばして頂いても結構です。


### Kotlinでの記述があるディレクトリ

---
`/app/src/main/java/com/smoothapp/notionshortcut`  
本ディレクトにKotlinで記述されたソースコードが格納され、最も実装力を確認できると思います。

`/controller`
実際にAPIとの通信を担うクラスや、utilityクラスが格納されています。

`/model`
主にデータを扱うクラスが格納されています。

`/view`
主に画面表示を担うクラスが格納されています。



### その他リソース格納されているディレクトリ

---
`/app/src/main/res`  
アプリで使用する各種リソースをxmlで定義しています。

`/layout`
画面構成のレイアウトファイルが格納されています。

`/values`
色や言語の定義ファイルが格納されています。

## 見てほしいところ

### オブジェクト指向

---
`/app/src/main/java/com/smoothapp/notionshortcut/model/notiondatabaseproperty`   
このディレクトリ内のデータクラスは全て`NotionDatabaseProperty`を継承している設計となっており、親クラスとの相互変換が可能です。
親クラスのデータはデータベースとの互換を意識し全てのデータを文字列として扱います。

### カスタムview

---
`/app/src/main/java/com/smoothapp/notionshortcut/view/component/notionshortcut`  
`/app/src/main/res/layout/view_*.xml`

viewの処理をactivityやfragmentに全て集約するのでは無く、カスタムviewを作成し、view自体にに処理を行わせることにより、
可読性や再利用性が高い設計となっています。







## 見ないで欲しいところ

### Activityクラス

---
現状主に`ShortcutActivity.kt`を中心として動作していますが、かなりテスト用のコードで埋めつくされています。  
現状は末端のクラスの方が実装がまとまっています。

### コメント

---
作業中なので、todoコメントが多数残っています。加えてKDoc(javadocの版)も全然書いていません。  
(まだリファクタリングが多いので終盤に書きます。)

### その他

---
* DBがまだ無いです。(Room Databaseを使用予定)
* 文字列のハードコーディングが多いです。(strings.xmlにまとめます)
