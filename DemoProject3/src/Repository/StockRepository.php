<?php

namespace App\Repository;

use App\Entity\Stock;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\ORM\OptimisticLockException;
use Doctrine\ORM\ORMException;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Stock|null find($id, $lockMode = null, $lockVersion = null)
 * @method Stock|null findOneBy(array $criteria, array $orderBy = null)
 * @method Stock[]    findAll()
 * @method Stock[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class StockRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Stock::class);
    }

    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function add(Stock $entity, bool $flush = true): void
    {
        $this->_em->persist($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }

    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function remove(Stock $entity, bool $flush = true): void
    {
        $this->_em->remove($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }

    // /**
    //  * @return Stock[] Returns an array of Stock objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('s')
            ->andWhere('s.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('s.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Stock
    {
        return $this->createQueryBuilder('s')
            ->andWhere('s.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */



    ################################################ STAT FRONT ###########################################################################"" 

   public function selectNomMarque()
   {
    return $this->getEntityManager()
    ->createQuery(
        'SELECT s.qualites as qualite ,p.nommarquep as nomM
        FROM App\Entity\Stock s ,App\Entity\Partenaires p
        WHERE s.partenaire =p.idp'
    )
    
    ->getResult();
   }

################################################ STAT BACK PIE CHART ###########################################################################"" 

   public function selectNomStockQte()
   {
    return $this->getEntityManager()
    ->createQuery(
        'SELECT s.qtes as quantite ,s.noms as nomStock
        FROM App\Entity\Stock s'
        
    )
    
    ->getResult();
   }



################################################ MAIL PARTENAIRE QTE<10 ###########################################################################"" 

public function selectMailQte()
{
    return $this->getEntityManager()
    ->createQuery(
        'SELECT p.mailp as mail 
        FROM App\Entity\Stock s ,App\Entity\Partenaires p
        WHERE s.partenaire =p.idp
        and s.qtes <10'
    )
    
    ->getResult();
     
 
}

 ################################################TRIE STOCK PAR QTE DESC ###########################################################################"" 


   public function TRIE()
   {
    return $this->createQueryBuilder('s')
    ->addOrderBy('s.qtes', 'DESC')
    ->getQuery()
    ->getResult()
;
        

    }
}
